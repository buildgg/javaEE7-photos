package ua.home.myphotos.model;

import ua.home.myphotos.exception.ValidationException;

/**
 * Created by vov on 12.07.2017.
 */
public enum SortMode {
    POPULAR_PHOTO,
    POPULAR_AUTHOR;

    public static SortMode of(String name){
      for (SortMode sortMode : SortMode.values()){
          if (sortMode.name().equalsIgnoreCase(name)){
              return sortMode;
          }
      }
      throw new ValidationException("Undefined sort mode: " + String.valueOf(name).toUpperCase());
    }



}
