/* 
 * @(#)Counter64.java        1.0 18/02/16
 *
 * This file has been auto-generated by JNC, the
 * Java output format plug-in of pyang.
 * Origin: module "ietf-yang-types", revision: "2013-07-15".
 */

package gen.addressPool.ietfYangTypes;

import com.tailf.jnc.YangException;
import com.tailf.jnc.YangUInt64;

import java.math.BigInteger;

/**
 * This class represents an element from 
 * the namespace 
 * generated to "src/gen/addressPool/ietfYangTypes/counter64"
 * <p>
 * See line 116 in
 * /usr/local/confd/src/confd/yang/ietf-yang-types.yang
 *
 * @version 1.0 2016-02-18
 * @author Auto Generated
 */
public class Counter64 extends YangUInt64 {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for Counter64 object from a string.
     * @param value Value to construct the Counter64 from.
     */
    public Counter64(String value) throws YangException {
        super(value);
        check();
    }

    /**
     * Constructor for Counter64 object from a BigInteger.
     * @param value Value to construct the Counter64 from.
     */
    public Counter64(BigInteger value) throws YangException {
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
     * Sets the value using a value of type BigInteger.
     * @param value The value to set.
     */
    public void setValue(BigInteger value) throws YangException {
        super.setValue(value);
        check();
    }

    /**
     * Checks all restrictions (if any).
     */
    public void check() throws YangException {
    }

}
