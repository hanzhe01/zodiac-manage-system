package com.youlintech.zodiac.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlintech.zodiac.web.domain.Pet;
import com.youlintech.zodiac.web.service.PetService;
import com.youlintech.zodiac.web.mapper.PetMapper;
import org.springframework.stereotype.Service;

/**
* @author adc
* @description 针对表【tb_pet】的数据库操作Service实现
* @createDate 2024-07-25 11:25:36
*/
@Service
public class PetServiceImpl extends ServiceImpl<PetMapper, Pet>
    implements PetService{

}




