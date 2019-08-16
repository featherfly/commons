
package cn.featherfly.common.lang.number;

/**
 * <p>
 * LocaleNumber
 * </p>
 * <p>
 * 2019-08-16
 * </p>
 *
 * @author zhongj
 */
public interface LocaleNumber {

    /**
     * get number display with locale langurage word
     *
     * @param number number
     * @return word
     */
    String toNumberWord(int number);

    /**
     * get number display with locale langurage word
     *
     * @param number number
     * @return word
     */
    String toNumberWord(long number);

    /**
     * get number display with locale langurage word
     *
     * @param number number
     * @return word
     */
    String toMoneyNumberWord(double number);
}
