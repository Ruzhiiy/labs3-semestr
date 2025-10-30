package com.example.lab1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListMapController {

    ArrayList<String> arraylist = null;
    HashMap<Integer, String> hashmap = null;
    Integer index = 1;

    @GetMapping("/update-array")
    public String updateArrayList(@RequestParam(value = "arg", required = false) String s){
        if(arraylist == null){
            arraylist = new ArrayList<>();
        }
        if(s == null || s.trim().isEmpty()){
            return "<h1>Ошибка: необходимо передать параметр arg</h1>" +
                   "<p>Используйте: <pre>/update-array?arg=<значение></pre></p>";
        }
        arraylist.add(s);
        return String.format("<h1>Добавлен: %s</h1>", s);
    }

    @GetMapping("/show-array")
    public String showArrayList(){
        if (arraylist==null) {
            return "<h1>Добавьте хотя бы один элемент в список</h1>" +
            "<p>Используйте метод <pre>/update-array&arg=<String><pre>";
        }else{
            String r = String.join(", ", arraylist);
            return String.format("<h1>Список:<h1><br><h1>%s</h1>", r);
        }
    }
        

    @GetMapping("/update-map")    
    public String updateMap(@RequestParam(value = "arg", required = false) String s){
        if(hashmap == null){
            hashmap = new HashMap<>();
        }
        if(s == null || s.trim().isEmpty()){
            return "<h1>Ошибка: необходимо передать параметр arg</h1>" +
                   "<p>Используйте: <pre>/update-map?arg=<значение></pre></p>";
        }
        hashmap.put(index, s);
        String inserted = String.format("Добавлен элемент %d:  %s", index, s);
        index++;
        return String.format("<h1>%s</h1>", inserted);
    }    
        
    @GetMapping("/show-map")
    public String showMap(){
        if (hashmap==null) {
            return "<h1>Добавьте хотя бы один элемент в hashmap</h1>" +
            "<p>Используйте метод <pre>/update-map&arg=<String><pre>";
        }else{
            String r = hashmap.entrySet().stream()
            .map(entry-> entry.getKey().toString() + ": " + entry.getValue())
            .collect(Collectors.joining(", "));
            return String.format("<h1>Map:</h1><br><h1>%s</h1>", r);
        }
    }


    @GetMapping("/show-all-lenght")
    public String showAllLenght(){
        String arr = (arraylist == null) ? "не создан" : String.valueOf(arraylist.size());
        String map = (hashmap == null) ? "не создан" : String.valueOf(hashmap.size());
        return String.format("<h1>Длина списка: %s</h1><br><h1>Длина map: %s</h1>", arr, map);
    }

}


