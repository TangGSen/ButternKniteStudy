package com.example;

import com.google.auto.service.AutoService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;


@AutoService(Processor.class)
public class BindViewProcessor extends AbstractProcessor {



    private Elements elementUtils;
    private Types typeUtils;
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        elementUtils = processingEnvironment.getElementUtils();
        typeUtils =processingEnvironment.getTypeUtils();
        filer =processingEnvironment.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        FileUtils.print("process:");
        Map<TypeElement,List<FindViewBind>> elementListMap = new HashMap<>();
        Set<Element> elements = (Set<Element>) roundEnvironment.getElementsAnnotatedWith(BindView.class);
        for (Element element:elements){

            TypeElement enEnclosingElement = (TypeElement) element.getEnclosingElement();
            List<FindViewBind> list = elementListMap.get(enEnclosingElement);
            if (list ==null){
                list = new ArrayList<>();
                elementListMap.put(enEnclosingElement,list);
            }
            String packageName = getPackageName(enEnclosingElement);
            FileUtils.print("packageName:"+packageName);
            int resId = element.getAnnotation(BindView.class).value();
            TypeMirror typeMirror = element.asType();
            FileUtils.print("typeMirror:"+typeMirror.getKind());
            FindViewBind findViewBind = new FindViewBind(packageName,typeMirror,resId);
            list.add(findViewBind);
        }




        return false;
    }

    private String getPackageName(TypeElement enEnclosingElement) {
        return elementUtils.getPackageOf(enEnclosingElement).getQualifiedName().toString();
    }


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> handler = new LinkedHashSet<>();
        handler.add(BindView.class.getCanonicalName());
        return handler;
    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return super.getSupportedSourceVersion();
    }



    //com.example.administrator.butterdepends.MainActivity
    private String getClassName(TypeElement enClosingElement, String packageName) {
        int packageLength=packageName.length()+1;
        return enClosingElement.getQualifiedName().toString().substring(packageLength).replace(".","$");
    }



}
