/*
package com.youlintech.zodiac.chatai.controller;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.youlintech.zodiac.chatai.domain.MaterialLibrary;
import com.youlintech.zodiac.chatai.enums.ZodiacEnum;
import com.youlintech.zodiac.chatai.model.MaterialLibraryAiPojo;
import com.youlintech.zodiac.chatai.service.MaterialLibraryService;
import com.youlintech.zodiac.chatai.utils.DateUtil;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;


@RestController
@RequestMapping("/chatAi")
public class ChatAiController {
    @Autowired
    private MaterialLibraryService materialLibraryService;
    private final OpenAiChatModel chatModel;

    @Autowired
    public ChatAiController(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/getChatAi")
    public MaterialLibraryAiPojo getChatAi(@RequestParam(value = "constellationName") String constellationName, @RequestParam(value = "number") Integer number, @RequestParam(value = "remark") String remark) throws ParseException {
//        String prompt = "预测一条"+constellationName+"的星座运势,返回json数据firstTitleContent第一主题内容string%20firstTitleName第一主题标题string%20firstTitleStar第一主题星级评价number(double)%20horoscopeFortune星座运势描述string%20horoscopeFortuneStar星座运势星级评价number(double)%20id星座IDinteger(int64)%20luckyColor幸运颜色string%20luckyItems幸运物品string%20luckyNumbers幸运数字integer(int64)%20luckyTime幸运时间string%20purchaseLink购物链接string%20secondTitleContent第二主题内容string%20secondTitleName第二主题标题string%20secondTitleStar第二主题星级评价number(double)%20starLevel星级水平number(double)%20suggestion建议信息string%20thirdTitleContent第三主题内容string%20thirdTitleName第三主题标题string%20thirdTitleStar第三主题星级评价number(double),幸运时间格式为08:00-10:00这样，其中运势内容丰富一点，完善一点，字数不少于100字";
        StringBuffer prompt = new StringBuffer();
        String numberCount = "预测" + number + "条" + constellationName + "的星座运势，返回json数据,内容为";
        String MaterialLibrary = "horoscopeFortune总体运势," +
                "horoscopeFortuneStar总体运势星级," +
                "luckyColor幸运色," +
                "luckyNumbers幸运数字integer(int64)," +
                "luckyTime幸运时间,幸运时间的格式为hh:mm-hh:mm," +
                "luckyItems幸运物，" +
                "purchaseLink幸运物品的购物链接，" +
                "firstTitleName运势1名字，" +
                "firstTitleContent运势1内容，" +
                "firstTitleStar运势1星级，" +
                "secondTitleName运势2名字，" +
                "secondTitleContent运势2内容，" +
                "secondTitleStar运势2星级，" +
                "thirdTitleName运势3名字，" +
                "thirdTitleContent运势3内容，" +
                "thirdTitleStar运势3星级，";
        prompt.append(numberCount);
        prompt.append(MaterialLibrary);
        prompt.append(remark);


        String response = chatModel.call(new Prompt(
                prompt.toString(),
                OpenAiChatOptions.builder()
                        .withModel("gpt-3.5-turbo")
                        .withTemperature(0.4F)
                        .build()

        )).getResult().getOutput().getContent();

        try {
            System.out.println(response);
            JSON.parseObject(response);
        } catch (Exception e) {
            return null;
        }

        MaterialLibraryAiPojo materialLibraryAiPojo = JSON.parseObject(response, MaterialLibraryAiPojo.class);

        Integer id = ZodiacEnum.getIdByName(constellationName);
        if (id != null) {
            materialLibraryAiPojo.setConstellationId(id.longValue());
        }
        MaterialLibrary materialLibrary = BeanUtil.copyProperties(materialLibraryAiPojo, MaterialLibrary.class);
        materialLibrary.setCreateBy("ChatAi");
        materialLibrary.setCreateTime(DateUtil.getNowDate());
        if (materialLibraryService.save(materialLibrary)) {
            return materialLibraryAiPojo;
        }
        return null;
    }

}
*/
