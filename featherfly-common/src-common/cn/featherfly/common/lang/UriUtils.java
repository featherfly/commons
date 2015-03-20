
/**
 * @author 钟冀 - yufei
 *		 	Aug 22, 2008
 */
package cn.featherfly.common.lang;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * uri的工具类
 * </p>
 * @author 钟冀
 */
public final class UriUtils {

	private UriUtils() {
	}
	/**
	 * uri路径分隔符
	 */
	public static final String SEPARATOR = "/";

	/**
	 * 转换 \ 为 /
	 * @param uri 待转换的uri
 	 * @return 转换后的字符串
	 */
	public static String convertSeparator(String uri) {
		return uri.replace("\\", SEPARATOR);
	}

	/**
	 * 返回以 / 分割 字符串（例如：/a/b/c/d/e，）后的字符串数组
	 * @param uri 待分割的uri
	 * @return 以/分割后的字符串数组
	 */
	public static String[] splitUri(String uri) {
		String[] uris = null;
		uri = removeStartAndEndSeparator(uri);
		if (uri.contains(SEPARATOR)) {
			uris = uri.split(SEPARATOR);
		}
		return uris;
	}

	/**
	 * link startUri and endUri ignoreCase the separator end of startUri and
	 * the separator start and end of endUri
	 * linkUri("/a/b/","/c/d/") return /a/b/c/d
	 *
	 * @param startUri 开始uri
	 * @param endUri 结束uri
	 * @return 连接后的url
	 */
	public static String linkUri(String startUri, String endUri) {
		return linkUri(startUri, endUri, true);
	}

	/**
	 * link startUri and endUri ignoreCase the separator end of startUri and
	 * the separator start and end of endUri
	 * linkUri("/a/b/","/c/d/") return /a/b/c/d
	 * if ignoreCaseEmpty is true and the startUri is empty,will return endUri
	 * if ignoreCaseEmpty = true linkUri("","c/d/") return c/d
	 * if ignoreCaseEmpty = false linkUri("","c/d/") return /c/d
	 * @param startUri 开始uri
	 * @param endUri 结束uri
	 * @param ignoreCaseEmpty 如果开始uri为空，则忽略
	 * @return 连接后的uri
	 */
	public static String linkUri(String startUri, String endUri, boolean ignoreCaseEmpty) {
		if (ignoreCaseEmpty && StringUtils.isEmpty(startUri)) {
			return endUri;
		}
		if (ignoreCaseEmpty && StringUtils.isEmpty(endUri)) {
			return startUri;
		}
		startUri = removeEndSeparator(startUri);
		endUri = removeStartAndEndSeparator(endUri);
		return startUri + UriUtils.SEPARATOR + endUri;
	}

	/**
	 * link given uris as given linkUri("/a/b/","c/d","/11/22/","/33/44/")
	 * return /a/b/c/d/11/22/33/44
	 *
	 * @param uris 待连接的uri
	 *
	 * @return 连接后的uri
	 */
	public static String linkUri(String... uris) {
		return linkUri(true, uris);
	}
	/**
	 * link given uris as given linkUri("/a/b/","c/d","/11/22/","/33/44/")
	 * return /a/b/c/d/11/22/33/44
	 * if ignoreCaseEmpty is true and the beforeUri is empty,will return afterUri
	 * if ignoreCaseEmpty = true linkUri("","","c/d") return c/d
	 * if ignoreCaseEmpty = false linkUri("","","c/d") return /c/d
	 * @param ignoreCaseEmpty 如果开始uri为空，则忽略
	 * @param uris 待连接的uri
	 * @return 连接后的uri
	 */
	public static String linkUri(boolean ignoreCaseEmpty, String... uris) {
		String resultUri = null;
		if (uris != null && uris.length > 0) {
			resultUri = uris[0];
			if (uris.length > 1) {
				for (int i = 1; i < uris.length; i++) {
					resultUri = linkUri(resultUri, uris[i], ignoreCaseEmpty);
				}
			}
		}
		return resultUri;
	}

	/**
	 * remove start separator of uri /aaa/ -> aaa/
	 *
	 * @param uri 待处理的uri
	 * @return 移除开始/后的uri
	 */
	public static String removeStartSeparator(String uri) {
		// TODO 使用循环来判断，出现不是UriUtils.SEPARATOR的直接以当前index为界返回substring
		while (uri.startsWith(UriUtils.SEPARATOR)) {
			uri = uri.substring(1, uri.length());
		}
		return uri;
	}

	/**
	 * remove end separator of uri /aaa/ -> /aaa
	 *
	 * @param uri 待处理的uri
	 * @return 移除结尾/后的uri
	 */
	public static String removeEndSeparator(String uri) {
		// TODO 使用循环来判断（倒序），出现不是UriUtils.SEPARATOR的直接以当前index为界返回substring		
		while (uri.endsWith(UriUtils.SEPARATOR)) {
			uri = uri.substring(0, uri.length() - 1);
		}
		return uri;
	}

	/**
	 * remove start and end separator of uri /aaa/ -> aaa
	 *
	 * @param uri 待处理的uri
	 * @return 移除开始结束/后的uri
	 */
	public static String removeStartAndEndSeparator(String uri) {
		uri = removeStartSeparator(uri);
		uri = removeEndSeparator(uri);
		return uri;
	}
}
