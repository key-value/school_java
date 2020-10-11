package com.sixteen.school.services;

import com.sixteen.school.model.Glass;
import com.sixteen.school.model.Grade;
import com.sixteen.school.repository.GlassRepository;
import com.sixteen.school.repository.GradeRepository;
import com.sixteen.school.star.UnifiedService;
import lombok.experimental.Delegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class GlassService {

    @Delegate
    UnifiedService<Glass> unifiedService;

    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    GlassScheduleService testService;

    private final GlassRepository glassRepository;

    public GlassService(GlassRepository glassRepository) {
        this.unifiedService = new UnifiedService<>(glassRepository);
        this.glassRepository = glassRepository;
    }


    public void test(){
        testService.getName();
    }

    public  List<Pair<Glass, Grade>> getGlassDetail(){
        List<Glass> glasses = glassRepository.findAll();
        Map<Long, Grade> gradeMap = getGradeMap(glasses);
        List<Pair<Glass, Grade>> result = glasses.stream().map(glass ->  Pair.of(glass,gradeMap.get(glass.getGradeId()))).collect(Collectors.toList());
        return result;
    }



    public Map<Long, Grade> getGradeMap(List<Glass> glasses) {
        List<Long> gradeIdList =  glasses.stream().map(Glass::getGradeId).distinct().collect(Collectors.toList());
        List<Grade> gradeList = gradeRepository.findAllById(gradeIdList);
        Map<Long, Grade> gradeMap = gradeList.stream().collect(Collectors.toMap(Grade::getId, Function.identity()));
        return gradeMap;
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

//    public Page<Glass> getPageList(   Pageable pageable){
//        Page<Glass> glassList= glassRepository.findAll(pageable);
//        return glassList ;
//    }
}
