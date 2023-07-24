package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source     source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {

        List<String> list = new ArrayList<>();
        list.add(source);
        for (String d : delimiters) {
            List<String> resp = foo(list, d);
            list = resp;
        }

        List<Integer> index = new ArrayList<>();
        list.removeIf(item -> item.length() == 0);

        return list;
    }

    private static List<String> foo(List<String> str, String delimeter) {
        //
        List<String> result = new ArrayList<>();
        for (String st : str) {
            String[] splitted = st.split(delimeter);
            result.addAll(List.of(splitted));
        }

        return result;
    }
}
