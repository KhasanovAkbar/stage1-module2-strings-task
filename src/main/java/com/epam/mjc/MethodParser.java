package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String accessModifier;
        String returnType;
        String methodName;

        List<MethodSignature.Argument> arguments = new ArrayList<>();


        String[] modifiers = {"public", "private", "protected"};

        String[] words = signatureString.split(" ");
        List<String> list = new ArrayList<>();

        for (String word : words) {
            if (!word.equals(""))
                list.add(word.trim());
        }

        boolean isTrue = false;

        for (String modifier : modifiers) {
            if (modifier.contains(list.get(0))) {
                isTrue = true;
                break;
            }
        }
        if (isTrue) {
            accessModifier = list.get(0);
            returnType = list.get(1);
            methodName = methodN(list.get(2))[0];
            if (!methodN(list.get(2))[1].equals(")")) {
                String name = methodN(list.get(2))[1];

                list.remove(accessModifier);
                list.remove(returnType);
                arguments = getArguments(list, name);
            }

        } else {
            accessModifier = null;
            returnType = list.get(0);
            methodName = methodN(list.get(1))[0];
            if (!methodN(list.get(1))[1].equals(")")) {
                String name = methodN(list.get(1))[1];

                list.remove(returnType);
                list.remove(methodName);
                arguments = getArguments(list, name);
            }
        }

        MethodSignature methodSignature = new MethodSignature(methodName, arguments);
        methodSignature.setAccessModifier(accessModifier);
        methodSignature.setReturnType(returnType);


        return methodSignature;
    }

    private List<MethodSignature.Argument> getArguments(List<String> list, String s) {

        List<MethodSignature.Argument> returnArguments = new ArrayList<>();
        MethodSignature.Argument argument = new MethodSignature.Argument(s, (String) list.get(1).subSequence(0, list.get(1).length() - 1));
        returnArguments.add(argument);
        list.remove(0);
        list.remove(0);
        for (int j = 0; j < list.size() - 1; j+=2) {
            argument = new MethodSignature.Argument(list.get(j), (String) list.get(j + 1).subSequence(0, list.get(j + 1).length() - 1));
            returnArguments.add(argument);
        }
        return returnArguments;
    }


    private String[] methodN(String word) {
        return word.split("\\(");
    }


}
