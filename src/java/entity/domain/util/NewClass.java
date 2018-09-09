/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.domain.util;

import java.net.URL;

/**
 *
 * @author sawad
 */
public class NewClass {

    public static void main(String arg[]) {
//        System.out.println(iStream);
//        String s = "C:\\Users\\sawad\\Documents\\NetBeansProjects\\Shifaa\\web\\resources\\images\\services\\20180909135232925persist.PNG";
//        System.out.println(s.replace("\\", "\\\\"));
        new NewClass().getFile();
    }

    public void getFile() {
//        ClassLoader classLoader = getClass().getClassLoader();

        URL url = getClass().getResource("/");
        System.out.println("is.......... " + url.getPath());

    }
}
