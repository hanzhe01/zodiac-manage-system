package com.youlintech.zodiac.chatai.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlintech.zodiac.chatai.common.entity.ResponseEntity;
import com.youlintech.zodiac.chatai.domain.MaterialLibrary;
import com.youlintech.zodiac.chatai.enums.ZodiacEnum;
import com.youlintech.zodiac.chatai.mapper.MaterialLibraryMapper;
import com.youlintech.zodiac.chatai.model.MaterialLibraryAiPojo;
import com.youlintech.zodiac.chatai.service.MaterialLibraryService;
import com.youlintech.zodiac.chatai.utils.DateUtil;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author adc
 * @description 针对表【tb_material_library】的数据库操作Service实现
 * @createDate 2024-07-26 12:01:04
 */
@Service
public class MaterialLibraryServiceImpl extends ServiceImpl<MaterialLibraryMapper, MaterialLibrary>
        implements MaterialLibraryService {

    private final OpenAiChatModel chatModel;
    @Autowired
    public MaterialLibraryServiceImpl(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public ResponseEntity<List<MaterialLibraryAiPojo>> autoAiGeneration(String constellationName, Integer number, String remark) {
        StringBuffer prompt = new StringBuffer();
        String numberCount = "预测1条" + constellationName + "的星座运势，返回json数据,内容为";
        String materialLibraryPrompt = "horoscopeFortune总体运势," +
                "horoscopeFortuneStar总体运势星级," +
                "luckyColor幸运色," +
                "luckyNumbers幸运数字一个integer(int64)类型的数字," +
                "luckyTime幸运时间,幸运时间的格式为hh:mm-hh:mm," +
                "luckyItems幸运物，" +
                "purchaseLink幸运物品的购物链接，" +
                "firstTitleName运势1名字为LOVE HOROSCOPE，" +
                "firstTitleContent运势1内容，" +
                "firstTitleStar运势1星级，" +
                "secondTitleName运势2名字为WORK HOROSCOPE，" +
                "secondTitleContent运势2内容，" +
                "secondTitleStar运势2星级，" +
                "thirdTitleName运势3名字为HEALTH HOROSCOPE，" +
                "thirdTitleContent运势3内容，" +
                "thirdTitleStar运势3星级，星级为0.0-5.0的一个数字,运势的内容要丰富，运势名字固定生成即可不少于100字";
        prompt.append(numberCount);
        prompt.append(materialLibraryPrompt);
        prompt.append(remark);
        /**
         * 星座的id
         */
        Integer id = ZodiacEnum.getIdByName(constellationName);
        List<MaterialLibrary> list = new ArrayList<>();

        /**
         * 当生成数量大于2或者等于2的时候
         */
        for (int i = 0; i < number; i++) {
            String response = chatModel.call(new Prompt(
                    prompt.toString(),
                    OpenAiChatOptions.builder()
                            .withModel("gpt-3.5-turbo")
                            .withTemperature(0.4F)
                            .build()
            )).getResult().getOutput().getContent();
            try {
                JSON.parseObject(response);
            } catch (Exception e) {
                return new ResponseEntity(false, "数据格式错误", HttpStatus.REQUEST_TIMEOUT.toString(), null);
            }

            MaterialLibraryAiPojo materialLibraryAiPojo = JSON.parseObject(response, MaterialLibraryAiPojo.class);
            materialLibraryAiPojo.setConstellationId(id.longValue());
            if (id == null) {
                return new ResponseEntity(false, "id获取失败", HttpStatus.NOT_ACCEPTABLE.toString(), null);
            }
            MaterialLibrary materialLibrary = BeanUtil.copyProperties(materialLibraryAiPojo, MaterialLibrary.class);
            materialLibrary.setCreateBy("ChatAi");
            materialLibrary.setCreateTime(DateUtil.getNowDate());
            list.add(materialLibrary);
        }
        if (list.size() > 0 && this.saveBatch(list)) {
            return new ResponseEntity(true, "数据保存成功", HttpStatus.OK.toString(), list);
        }
        return new ResponseEntity(false, "数据保存失败", HttpStatus.NOT_ACCEPTABLE.toString(), list);
    }
}






