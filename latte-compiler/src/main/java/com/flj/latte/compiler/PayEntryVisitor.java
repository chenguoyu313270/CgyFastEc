package com.flj.latte.compiler;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

/** SimpleAnnotationValueVisitor7
 * Created by Administrator on 2018\6\4 0004.
 */

public class PayEntryVisitor extends SimpleAnnotationValueVisitor7<Void,Void> {
    private final Filer FILER;
    private String mPackageName = null;


    public PayEntryVisitor(Filer filer) {
        FILER = filer;
    }



    @Override
    public Void visitString(String s,Void p){
        mPackageName=s;
        return p;
    }
    private void generateJavaCode(TypeMirror typeMirror) {
        final TypeSpec targetActivity =
                TypeSpec.classBuilder("WXPayEntryActivity")
                        .addModifiers(Modifier.PUBLIC)
                        .addModifiers(Modifier.FINAL)
                        .superclass(TypeName.get(typeMirror))
                        .build();
//微信支付入口文件
        final JavaFile javaFile = JavaFile.builder(mPackageName + ".wxapi", targetActivity)
                .addFileComment("微信支付入口文件")
                .build();
        try {
            javaFile.writeTo(FILER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
