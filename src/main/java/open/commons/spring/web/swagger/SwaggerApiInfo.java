/*
 *
 * This file is generated under this project, "open-commons-spring-web".
 *
 * Date  : 2020. 10. 30. 오후 4:22:51
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.swagger;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.models.Contact;
import springfox.documentation.service.ApiInfo;

/**
 * {@link ApiInfo} 정보 클래스.
 * 
 * @since 2020. 10. 30.
 * @version
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class SwaggerApiInfo {

    /** {@link ApiInfo#getTitle()} */
    @NotNull
    @NotEmpty
    private String title;
    /** {@link ApiInfo#getDescription()} */
    private String description;
    /** {@link ApiInfo#getVersion()} */
    @NotNull
    @NotEmpty
    private String version;
    /** {@link ApiInfo#getTermsOfServiceUrl()} */
    private String termsOfServiceUrl;
    /**
     * @see {@link ApiInfo#getContact()}
     */
    private Contact contactName;
    /** {@link ApiInfo#getLicense()} */
    private String license;
    /** {@link ApiInfo#getLicenseUrl()} */
    private String licenseUrl;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 30.		박준홍			최초 작성
     * </pre>
     *
     * @since 2020. 10. 30.
     */
    public SwaggerApiInfo() {
    }

    /**
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 30.		박준홍			최초 작성
     * </pre>
     * 
     * @return the contactName
     *
     * @since 2020. 10. 30.
     * 
     * @see #contactName
     */
    public Contact getContactName() {
        return contactName;
    }

    /**
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 30.		박준홍			최초 작성
     * </pre>
     * 
     * @return the description
     *
     * @since 2020. 10. 30.
     * 
     * @see #description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 30.		박준홍			최초 작성
     * </pre>
     * 
     * @return the license
     *
     * @since 2020. 10. 30.
     * 
     * @see #license
     */
    public String getLicense() {
        return license;
    }

    /**
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 30.		박준홍			최초 작성
     * </pre>
     * 
     * @return the licenseUrl
     *
     * @since 2020. 10. 30.
     * 
     * @see #licenseUrl
     */
    public String getLicenseUrl() {
        return licenseUrl;
    }

    /**
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 30.		박준홍			최초 작성
     * </pre>
     * 
     * @return the termsOfServiceUrl
     *
     * @since 2020. 10. 30.
     * 
     * @see #termsOfServiceUrl
     */
    public String getTermsOfServiceUrl() {
        return termsOfServiceUrl;
    }

    /**
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 30.		박준홍			최초 작성
     * </pre>
     * 
     * @return the title
     *
     * @since 2020. 10. 30.
     * 
     * @see #title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 30.		박준홍			최초 작성
     * </pre>
     * 
     * @return the version
     *
     * @since 2020. 10. 30.
     * 
     * @see #version
     */
    public String getVersion() {
        return version;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 30.		박준홍			최초 작성
     * </pre>
     *
     * @param contactName
     *            the contactName to set
     *
     * @since 2020. 10. 30.
     * 
     * @see #contactName
     */
    public void setContactName(Contact contactName) {
        this.contactName = contactName;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 30.		박준홍			최초 작성
     * </pre>
     *
     * @param description
     *            the description to set
     *
     * @since 2020. 10. 30.
     * 
     * @see #description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 30.		박준홍			최초 작성
     * </pre>
     *
     * @param license
     *            the license to set
     *
     * @since 2020. 10. 30.
     * 
     * @see #license
     */
    public void setLicense(String license) {
        this.license = license;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 30.		박준홍			최초 작성
     * </pre>
     *
     * @param licenseUrl
     *            the licenseUrl to set
     *
     * @since 2020. 10. 30.
     * 
     * @see #licenseUrl
     */
    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 30.		박준홍			최초 작성
     * </pre>
     *
     * @param termsOfServiceUrl
     *            the termsOfServiceUrl to set
     *
     * @since 2020. 10. 30.
     * 
     * @see #termsOfServiceUrl
     */
    public void setTermsOfServiceUrl(String termsOfServiceUrl) {
        this.termsOfServiceUrl = termsOfServiceUrl;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 30.		박준홍			최초 작성
     * </pre>
     *
     * @param title
     *            the title to set
     *
     * @since 2020. 10. 30.
     * 
     * @see #title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 30.		박준홍			최초 작성
     * </pre>
     *
     * @param version
     *            the version to set
     *
     * @since 2020. 10. 30.
     * 
     * @see #version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @since 2020. 10. 30.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SwaggerApiInfo [title=");
        builder.append(title);
        builder.append(", description=");
        builder.append(description);
        builder.append(", version=");
        builder.append(version);
        builder.append(", termsOfServiceUrl=");
        builder.append(termsOfServiceUrl);
        builder.append(", contactName=");
        builder.append(contactName);
        builder.append(", license=");
        builder.append(license);
        builder.append(", licenseUrl=");
        builder.append(licenseUrl);
        builder.append("]");
        return builder.toString();
    }
}
