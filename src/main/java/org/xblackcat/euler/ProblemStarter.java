package org.xblackcat.euler;

import org.xblackcat.euler.ann.EntryPoint;
import org.xblackcat.euler.ann.InputData;
import org.xblackcat.euler.ann.ResultDescription;
import org.xblackcat.euler.util.ParserUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.text.MessageFormat;
import java.util.Locale;

/**
 * 21.11.2017 10:18
 *
 * @author xBlackCat
 */
public class ProblemStarter {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Run all the solved problems");

            for (int i = 0; i < 1000; i++) {
                try {
                    runProblem(i);
                } catch (ClassNotFoundException e) {
                    // ignore
                }
            }
            return;
        }

        try {
            int problemNumber = Integer.parseInt(args[0]);
            try {
                runProblem(problemNumber);
            } catch (ClassNotFoundException e) {
                System.out.println("Problem #" + problemNumber + " is not found");
            }
        } catch (NumberFormatException e) {
            System.out.println("First argument should be number of problem");
        }
    }

    private static void runProblem(int n) throws ClassNotFoundException {
        String className = "org.xblackcat.euler.problem" + n + ".Problem" + n;
        Class<?> problemClass = Class.forName(className);

        Method method = getEntryPoint(problemClass);
        if (method == null) {
            System.out.println("No entry point found for Problem #" + n);
            return;
        }
        InputData inputDataAnn = problemClass.getAnnotation(InputData.class);
        Object[] inputData = prepareData(method, inputDataAnn);

        ResultDescription resultDescriptionAnn = problemClass.getAnnotation(ResultDescription.class);

        final String resultString;
        if (resultDescriptionAnn == null) {
            StringBuilder builder = new StringBuilder("Problem #" + n + " result is {0} for input data:");
            int i = 1;
            for (Object ignored : inputData) {
                builder.append(" {");
                builder.append(i++);
                builder.append("}");
            }
            resultString = builder.toString();
        } else {
            resultString = resultDescriptionAnn.value();
        }

        try {
            System.out.println("### Run Problem #" + n);
            Object problemInstance = problemClass.getDeclaredConstructor().newInstance();

            long start = System.currentTimeMillis();
            Object result = method.invoke(problemInstance, inputData);
            long duration = System.currentTimeMillis() - start;

            Object[] messageArgs = new Object[inputData.length + 1];
            messageArgs[0] = result;
            System.arraycopy(inputData, 0, messageArgs, 1, inputData.length);

            System.out.println("Problem #" + n + " successfully executed in " + duration + " ms.");
            MessageFormat messageFormat = new MessageFormat(resultString, Locale.ROOT);
            System.out.println(messageFormat.format(messageArgs));
            System.out.println();

        } catch (ReflectiveOperationException e) {
            System.err.println("Instantiate problem is failed");
            e.printStackTrace(System.err);
        }
    }

    private static Object[] prepareData(Method method, InputData inputDataAnn) {
        Parameter[] parameters = method.getParameters();
        Object[] args = new Object[parameters.length];
        String[] strings = inputDataAnn.value();
        if (strings.length != parameters.length) {
            throw new IllegalArgumentException("Amount of input data arguments not matched method parameters count");
        }
        for (int i = 0; i < parameters.length; i++) {
            args[i] = ParserUtils.getToObjectConverter(parameters[i].getType()).apply(strings[i]);
        }
        return args;
    }

    private static Method getEntryPoint(Class<?> problemClass) {
        for (Method m : problemClass.getMethods()) {
            if (m.isAnnotationPresent(EntryPoint.class)) {
                return m;
            }
        }
        return null;
    }
}
