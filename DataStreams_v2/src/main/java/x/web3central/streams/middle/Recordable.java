package x.web3central.streams.middle;

public interface Recordable {

	
	public DataFormat getDataFormat();
	public ExportFormat[] getConvertableFormats();
	public ExportFormat getBestConvertableFormat();
}
