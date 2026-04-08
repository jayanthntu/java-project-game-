package ui;

import java.util.List;

public class PrintDescribables 
{
    public static <T extends Describable> void printDescribable(T obj) {
        System.out.println(obj.getName() + " - " + obj.getDescription());
    }

    public static <T extends Describable> void printDescribables(String title, List<T> list) 
    {
        System.out.println("\n=== " + title.toUpperCase() + " ===");

        for (int i = 0; i < list.size(); i++) 
        {
            T obj = list.get(i);
            System.out.println((i + 1) + ". " + obj.getName() + " - " + obj.getDescription());
        }
    }
}