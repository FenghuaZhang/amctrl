/* 
 * @(#)Uuid.java        1.0 18/02/16
 *
 * This file has been auto-generated by JNC, the
 * Java output format plug-in of pyang.
 * Origin: module "ietf-yang-types", revision: "2013-07-15".
 */

package gen.addressPool.ietfYangTypes;

import com.tailf.jnc.YangException;
import com.tailf.jnc.YangString;

/**
 * This class represents an element from 
 * the namespace 
 * generated to "src/gen/addressPool/ietfYangTypes/uuid"
 * <p>
 * See line 445 in
 * /usr/local/confd/src/confd/yang/ietf-yang-types.yang
 *
 * @version 1.0 2016-02-18
 * @author Auto Generated
 */
public class Uuid extends YangString {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for Uuid object from a string.
     * @param value Value to construct the Uuid from.
     */
    public Uuid(String value) throws YangException {
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
