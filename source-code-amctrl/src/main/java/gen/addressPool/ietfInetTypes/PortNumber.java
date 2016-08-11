/* 
 * @(#)PortNumber.java        1.0 18/02/16
 *
 * This file has been auto-generated by JNC, the
 * Java output format plug-in of pyang.
 * Origin: module "ietf-inet-types", revision: "2013-07-15".
 */

package gen.addressPool.ietfInetTypes;

import com.tailf.jnc.YangException;
import com.tailf.jnc.YangUInt16;

/**
 * This class represents an element from 
 * the namespace 
 * generated to "src/gen/addressPool/ietfInetTypes/port-number"
 * <p>
 * See line 122 in
 * /usr/local/confd/src/confd/yang/ietf-inet-types.yang
 *
 * @version 1.0 2016-02-18
 * @author Auto Generated
 */
public class PortNumber extends YangUInt16 {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for PortNumber object from a string.
     * @param value Value to construct the PortNumber from.
     */
    public PortNumber(String value) throws YangException {
        super(value);
        check();
    }

    /**
     * Constructor for PortNumber object from a int.
     * @param value Value to construct the PortNumber from.
     */
    public PortNumber(int value) throws YangException {
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
     * Sets the value using a value of type int.
     * @param value The value to set.
     */
    public void setValue(int value) throws YangException {
        super.setValue(value);
        check();
    }

    /**
     * Checks all restrictions (if any).
     */
    public void check() throws YangException {
    }

}
