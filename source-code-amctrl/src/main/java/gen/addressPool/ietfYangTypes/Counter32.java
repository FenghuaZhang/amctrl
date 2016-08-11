/* 
 * @(#)Counter32.java        1.0 18/02/16
 *
 * This file has been auto-generated by JNC, the
 * Java output format plug-in of pyang.
 * Origin: module "ietf-yang-types", revision: "2013-07-15".
 */

package gen.addressPool.ietfYangTypes;

import com.tailf.jnc.YangException;
import com.tailf.jnc.YangUInt32;

/**
 * This class represents an element from 
 * the namespace 
 * generated to "src/gen/addressPool/ietfYangTypes/counter32"
 * <p>
 * See line 59 in
 * /usr/local/confd/src/confd/yang/ietf-yang-types.yang
 *
 * @version 1.0 2016-02-18
 * @author Auto Generated
 */
public class Counter32 extends YangUInt32 {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for Counter32 object from a string.
     * @param value Value to construct the Counter32 from.
     */
    public Counter32(String value) throws YangException {
        super(value);
        check();
    }

    /**
     * Constructor for Counter32 object from a long.
     * @param value Value to construct the Counter32 from.
     */
    public Counter32(long value) throws YangException {
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
     * Sets the value using a value of type long.
     * @param value The value to set.
     */
    public void setValue(long value) throws YangException {
        super.setValue(value);
        check();
    }

    /**
     * Checks all restrictions (if any).
     */
    public void check() throws YangException {
    }

}
