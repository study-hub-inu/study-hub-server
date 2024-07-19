package kr.co.studyhubinu.studyhubserver.common.redisson;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

@Slf4j
@NoArgsConstructor
public class CustomSpringELParser {
    public static String getDynamicValue(String[] parameterNames, Object[] args, String key) {
        SpelExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }
        Object value = parser.parseExpression(key).getValue(context, Object.class);
        if (value == null) {
            log.warn("CustomSpringELParser evaluated value is null for key={}", key);
            return null;
        }
        return value.toString();
    }
}