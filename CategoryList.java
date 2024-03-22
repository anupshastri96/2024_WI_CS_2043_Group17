package MiniProject;

import java.util.ArrayList;

public class CategoryList{
    private ArrayList<Category> cateList;
    //private final User client;

    public CategoryList(){
        cateList = new ArrayList<>(1);
    }

    public void addCategory(Category toAdd){
        cateList.add(toAdd);
    }

    public void addCategory(String name, String description){
        Category toAdd = new Category(name, description);
        cateList.add(toAdd);
    }

    public void removeCategory(int categoryId){
        //cateList.remove(categoryId-1);
        for(int i = 0; i < cateList.size(); i++){
            if(cateList.get(i).getCategoryId() == categoryId){
                cateList.remove(i);
            }
        }
    }

    public void removeCategory(String name){
        for(int i = 0; i < cateList.size(); i++){
            if(cateList.get(i).getCategoryName().equals(name)){
                cateList.remove(i);
            }
        }
    }

    public void editCategory(int categoryId, String newName, String newDes){
        //cateList.get(categoryId-1).setCategoryName(newName);
        //cateList.get(categoryId-1).setCategoryDescription(newDes);
        for(int i = 0; i < cateList.size(); i++){
            if(cateList.get(i).getCategoryId() == categoryId){
                cateList.get(i).setCategoryName(newName);
                cateList.get(i).setCategoryDescription(newDes);
            }
        }
    }

    public void editCategory(String name, String newName, String newDes){
        for(int i = 0; i < cateList.size(); i++){
            if(cateList.get(i).getCategoryName() == name){
                cateList.get(i).setCategoryName(newName);
                cateList.get(i).setCategoryDescription(newDes);
            }
        }
    }

    public String toString(){
        String toReturn = "";
        for(Category cate : cateList){
            toReturn += cate.toString() + "\n";
        }
        return toReturn;
    }
}
