package x.web3central.streams.middle;

import java.util.Map;

/*
 * - 1) This interface is implemented by all the DataContainer subclasses that store their data in a map
 * - 2) All the implemented classes will share the behavior of the Pairs interface
 * - 3) Examples
 *      - FormDataContainer
 *      - MetaDataContainer
 */


public interface Pairs {

	public Map<String, String> getData();
	public DataFormat getDataFormat();
	
}
