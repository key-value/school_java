package com.sixteen.school.services;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sixteen.school.mapper.GlassMapper;
import com.sixteen.school.model.Glass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GlassService {
    @Autowired
    public GlassMapper glassMapper;

    public long addGlass(Glass glass) {
        return glassMapper.insert(glass);
    }

    public int updateGlass(Glass glass) {
        return glassMapper.updateByPrimaryKeySelective(glass);
    }

    public int removeGlass(long id) {
        Glass glass = new Glass();
        glass.setId(id);
        return glassMapper.deleteByExample(glass);
    }

    public Glass getGlassById(Long  id) {
        return glassMapper.selectByPrimaryKey(id);
    }

    public List<Glass> getList(){
        return glassMapper.selectAll();
    }
    public PageInfo<Glass> getPageList(int pageSize, int pageIndex){
        PageHelper.startPage(pageIndex, pageSize);
        List<Glass> glassList= glassMapper.selectAll();
        PageInfo info=new PageInfo(glassList);
        return info ;
    }
}
