/*
 *
 * This file is generated under this project, "open-commons-spring-web".
 *
 * Date  : 2020. 9. 4. 오전 12:44:49
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import open.commons.spring.web.springfox.swagger.SpringfoxSwagger;

/**
 * 
 * @since 2020. 9. 4.
 * @version
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public abstract class SpringfoxSwaggerWebSecurityCofigurer extends WebSecurityConfigurerAdapter {

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 9. 4.		박준홍			최초 작성
     * </pre>
     *
     * @since 2020. 9. 4.
     */
    public SpringfoxSwaggerWebSecurityCofigurer() {
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 9. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param disableDefaults
     * @since 2020. 9. 4.
     */
    public SpringfoxSwaggerWebSecurityCofigurer(boolean disableDefaults) {
        super(disableDefaults);
    }

    /**
     * @since 2020. 9. 6.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(SpringfoxSwagger.getUrlList()).permitAll();
    }

    /**
     * @since 2020. 9. 4.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.WebSecurity)
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(SpringfoxSwagger.getUrlList());
    }
}
