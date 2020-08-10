package com.sixteen.school.services;

import com.sixteen.school.model.Glass;
import com.sixteen.school.repository.GlassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GlassService {
    @Autowired
    private GlassRepository glassRepository;

    public Glass addGlass(Glass glass) {
        return glassRepository.save(glass);
    }

    public Glass updateGlass(Glass glass) {
        return glassRepository.save(glass);
    }

    public void removeGlass(long id) {
        glassRepository.deleteById(id);
    }

    public Glass getGlassById(Long  id) {
        return glassRepository.findById(id).get();
    }

    public List<Glass> getList(){
        return glassRepository.findAll();
    }
    public Page<Glass> getPageList(   Pageable pageable){
        Page<Glass> glassList= glassRepository.findAll(pageable);
        return glassList ;
    }
}
