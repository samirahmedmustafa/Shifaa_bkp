/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.domain.util;

import java.util.List;

/**
 *
 * @author sawad
 */
public class Helper {

    public static String getAbsPath(String category) {
        String absolutePath = "C:\\Users\\sawad\\Documents\\NetBeansProjects\\Shifaa\\web\\resources\\images\\" + category + "\\";
        return absolutePath;
    }
    
    public static String getAppPath(String category) {
        String appPath = "../resources/images/" + category + "/";
        return appPath;
    }

}
