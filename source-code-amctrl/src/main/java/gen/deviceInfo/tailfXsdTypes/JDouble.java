/* 
 * @(#)JDouble.java        1.0 18/02/16
 *
 * This file has been auto-generated by JNC, the
 * Java output format plug-in of pyang.
 * Origin: module "tailf-xsd-types", revision: "2009-03-17".
 */

package gen.deviceInfo.tailfXsdTypes;

import com.tailf.jnc.YangException;
import com.tailf.jnc.YangString;

/**
 * This class represents an element from 
 * the namespace 
 * generated to "src/gen/deviceInfo/tailfXsdTypes/double"
 * <p>
 * See line 62 in
 * /usr/local/confd/src/confd/yang/tailf-xsd-types.yang
 *
 * @version 1.0 2016-02-18
 * @author Auto Generated
 */
public class JDouble extends YangString {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDouble object from a string.
     * @param value Value to construct the JDouble from.
     */
    public JDouble(String value) throws YangException {
        super(value);
        check();
    }

    /**
     * Sets the value using a string value.
     * @param value The value to set.
     */
    public void setValue(String value) throws YangException {
        super.setValue(value);
        check();
    }

    /**
     * Checks all restrictions (if any).
     */
    public void check() throws YangException {
    }

}
