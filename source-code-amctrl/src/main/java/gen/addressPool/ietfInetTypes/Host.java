/* 
 * @(#)Host.java        1.0 18/02/16
 *
 * This file has been auto-generated by JNC, the
 * Java output format plug-in of pyang.
 * Origin: module "ietf-inet-types", revision: "2013-07-15".
 */

package gen.addressPool.ietfInetTypes;

import com.tailf.jnc.YangException;
import com.tailf.jnc.YangUnion;

/**
 * This class represents an element from 
 * the namespace 
 * generated to "src/gen/addressPool/ietfInetTypes/host"
 * <p>
 * See line 408 in
 * /usr/local/confd/src/confd/yang/ietf-inet-types.yang
 *
 * @version 1.0 2016-02-18
 * @author Auto Generated
 */
public class Host extends YangUnion {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for Host object from a string.
     * @param value Value to construct the Host from.
     */
    public Host(String value) throws YangException {
        super(value,
            new String[] {
                "gen.addressPool.ietfInetTypes.IpAddress",
                "gen.addressPool.ietfInetTypes.DomainName",
            }
        );
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
