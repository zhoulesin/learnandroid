package com.zhoulesin.apt_processor2;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;

public class ClassCreatorProxy {

    private TypeElement mTypeElement;
    private String mPackageName;
    private String mBingingClassName;
    private Map<Integer, VariableElement> mVariableElementMap = new HashMap<>();

    public ClassCreatorProxy(Elements elementUtils, TypeElement classElement) {
        //类的信息
        this.mTypeElement = classElement;
        PackageElement packageEle = elementUtils.getPackageOf(classElement);
        String packageName = packageEle.getQualifiedName().toString();
        String className = classElement.getSimpleName().toString();
        this.mPackageName = packageName;
        this.mBingingClassName = className + "_ViewBinding";
    }


    public String getProxyClassFullName() {
        return this.mPackageName +"."+ this.mBingingClassName;
    }


    public void putElement(int id, VariableElement variableElement) {
        mVariableElementMap.put(id, variableElement);
    }

    public String generateJavaCode() {
        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(mPackageName).append(";\n\n");
        sb.append("import com.zhoulesin.apt_library.*;\n");
        sb.append("\n");
        sb.append("public class ").append(mBingingClassName);
        sb.append("{\n");
        generateMethods(sb);
        sb.append("}\n");
        return sb.toString();
    }

    private void generateMethods(StringBuilder sb) {
        sb.append("\tpublic void bind(").append(mTypeElement.getQualifiedName()).append(" host){\n");
        for (Integer key : mVariableElementMap.keySet()) {
            VariableElement element = mVariableElementMap.get(key);
            String eleName = element.getSimpleName().toString();
            String type = element.asType().toString();
            sb.append("\t\thost.").append(eleName).append("=");
            sb.append("(").append(type).append(")(((android.app.Activity)host).findViewById(").append(key).append("));\n");
        }
        sb.append("\t}\n");
    }

    public Element getTypeElement() {
        return mTypeElement;
    }
}
