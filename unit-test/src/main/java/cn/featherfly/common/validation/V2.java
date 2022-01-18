//
//package cn.featherfly.common.validation;
//
//import javax.validation.Validation;
//import javax.validation.Validator;
//import javax.validation.constraints.NotEmpty;
//
//import org.hibernate.validator.HibernateValidator;
//
///**
// * <p>
// * V
// * </p>
// * .
// *
// * @author zhongj
// */
//public class V2 {
//
//    /**
//     * Not empty.
//     *
//     * @param s the s
//     */
//    public void notEmpty(@NotEmpty(message = "not empty") String s) {
//        System.out.println("validation test not empty -> " + s);
//    }
//
//    /**
//     * The main method.
//     *
//     * @param args the arguments
//     */
//    public static void main(String[] args) {
//        new V2().notEmpty("");
//
//        Validator validator = Validation.byProvider(HibernateValidator.class).configure().failFast(false)
//                .buildValidatorFactory().getValidator();
//    }
//}
