package reflect;

import leetcode.Candy;
import org.junit.Test;

import java.lang.reflect.*;
import java.net.URLClassLoader;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author 18190
 * @Date: 2021/8/3  14:58
 * @VERSION 1.0
 */
public class Main {
    /**
     * 每个类写完源代码后 都会编译成.class文件 一个类生成一个.class文件（中间过渡的）
     * .class文件 包含这些源代码的所有信息 可以被虚拟机的指令集翻译成目标机器的机器语言
     * Class 类加载器在类使用时 将.class字节码文件由硬盘传递到内存
     * 如果这个类的Field域含有其他类型的Class对象 也会被加载
     *
     *
     * 引导类加载器（引导类加载器负责加载系统类 例如String,int 引导类加载器没有对应的ClassLoader对象）
     * 扩展类加载器 （扩展类加载器用于从jre/lib/ext目录加载“标准的扩展”，可以将JAR文件放入该目录，这样即使没有任何类路径，扩展类加载器也可以找到其中的各个类）
     * 系统类加载器（应用类加载器）
     *
     */
    @Test
    public void testClassloader(){
        /**
         * 引导类加载器没有对应的ClassLoader对象  null
         */
        System.out.println(String.class.getClassLoader());
        System.out.println(Integer.class.getClassLoader());
        System.out.println(int.class.getClassLoader());

        /**
         * 系统类加载器： sun.misc.Launcher$AppClassLoader
         * 类加载器有一种父/子关系。除了引导类加载器外，每个类加载器都有一个父类加载器
         * 根据规定，类加载器会为它的父类加载器提供一个机会，以便加载任何给定的类，并且只有在其父类加载器加载失败时，它才会加载该给定类。
         * 例如，当要求系统类加载器加载一个系统类（比如，java.util.ArrayList）时，
         * 它首先要求扩展类加载器进行加载，该扩展类加载器则首先要求引导类加载器进行加载。
         * 引导类加载器会找到并加载rt.jar中的这个类，而无须其他类加载器做更多的搜索。
         */
        System.out.println(Candy.class.getClassLoader());
        // 扩展类加载器
        System.out.println(Candy.class.getClassLoader().getParent());
        // 引导类加载器 null
        System.out.println(Candy.class.getClassLoader().getParent().getParent());


    }


    @Test
    public  void testReflect() throws Exception {


        Class aClass = Class.forName("reflect.Reflect");
        Reflect reflect = (Reflect) aClass.newInstance();
        Constructor[] declaredConstructors = aClass.getDeclaredConstructors();
        for (Constructor declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);

        }
        Constructor declaredConstructor = aClass.getDeclaredConstructor(String.class);
        declaredConstructor.setAccessible(true);
        Object zzl = declaredConstructor.newInstance("zzl");
        System.out.println(zzl.getClass().getName() + " " + zzl);

        System.out.println("-------------------------------");
        Method[] method = aClass.getDeclaredMethods();
        System.out.println(zzl);
        for (Method method1 : method) {
            System.out.println(method1);
            String[] strings = new String[method1.getParameterTypes().length];
            int i = 0;
            Object[] objects = new Object[method1.getParameterTypes().length];
            for (Class parameterType : method1.getParameterTypes()) {
                strings[i] = parameterType.getName();
                if(!"java.lang.Integer".equals(parameterType.getName())){

                    objects[i++] = Class.forName(parameterType.getName()).newInstance();
                }else {
                    objects[i++] = i*100;
                }
            }
            System.out.println(Arrays.toString(strings));
            System.out.println(method1.getName() + ": " + method1.invoke(zzl,objects));

        }
        System.out.println(zzl);
        System.out.println("-------------------------------");
        System.out.println();
        Field[] fields = aClass.getDeclaredFields();

        Map<String,Object> map = new HashMap<>();
        map.put("name","hfh");
        map.put("age",83);
        Candy candy = new Candy();
        candy.setI(45);
        candy.setS("hhh");
        map.put("candy",candy);
        printFields(aClass, zzl, fields);

        modifyFields(aClass, zzl, map);
        printFields(aClass, zzl, fields);

        Method m = aClass.getDeclaredMethod("hhh", String.class, Integer.class, String.class);
        m.invoke(zzl,"xxx",1,"ttt");
    }

    private void modifyFields(Class aClass, Object zzl, Map<String, Object> map) {
        map.forEach((k, v)->{
            try {
                Field declaredField = aClass.getDeclaredField(k);
                char[] cs=declaredField.getName().toCharArray();
                if(cs[0] <=122 && cs[0] >= 97){
                    cs[0]-=32;
                }
                Method method1 = null;
                if(declaredField.getType().getName().equals("int")){
                     method1 = aClass.getDeclaredMethod("set" +String.valueOf(cs) ,int.class);
                }
                else{
                    method1 = aClass.getDeclaredMethod("set" +String.valueOf(cs) ,Class.forName(declaredField.getType().getName()));
                }
                method1.invoke(zzl,v);
            } catch (NoSuchFieldException | NoSuchMethodException | ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        });
    }

    private void printFields(Class aClass, Object zzl, Field[] fields) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        for (Field field : fields) {
//            System.out.println(field);
//
//            System.out.println(Modifier.toString(field.getModifiers()));
//            System.out.println(field.getType().getName());
//            System.out.println(field.getDeclaringClass().getName());
//            System.out.println(field.getName());
            char[] cs=field.getName().toCharArray();
            if(cs[0] <=122 && cs[0] >= 97){
                cs[0]-=32;
            }


            Method method1 = aClass.getDeclaredMethod("get" +String.valueOf(cs) );
            System.out.println(method1);
            System.out.println("get" +String.valueOf(cs) + ": " +method1.invoke(zzl));
        }
    }
}
