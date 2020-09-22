package com.sixteen.school.services;

import com.sixteen.school.model.Glass;
import com.sixteen.school.repository.GlassRepository;
import com.sixteen.school.star.ITestService;
import com.sixteen.school.star.UnifiedService;
import lombok.experimental.Delegate;
import org.springframework.stereotype.Service;

@Service
public class GlassService {

    @Delegate
    UnifiedService<Glass> unifiedService;

    ITestService testService;

    private final GlassRepository glassRepository;

    public GlassService(GlassRepository glassRepository,ITestService testService) {
        this.unifiedService = new UnifiedService<>(glassRepository);
        this.glassRepository = glassRepository;
        this.testService = testService;
    }


    public void test(){
        testService.getName();
    }
//    public Glass addGlass(Glass glass) {
//        return glassRepository.save(glass);
//    }
//
//    public Glass updateGlass(Glass glass) {
//        return glassRepository.save(glass);
//    }
//
//    public void removeGlass(long id) {
//        glassRepository.deleteById(id);
//    }
//
//    public Glass getGlassById(Long  id) {
//        return glassRepository.findById(id).get();
//    }

//    public List<Glass> getList(){
//        return glassRepository.findAll();
//    }
//    public Page<Glass> getPageList(   Pageable pageable){
//        Page<Glass> glassList= glassRepository.findAll(pageable);
//        return glassList ;
//    }
}
