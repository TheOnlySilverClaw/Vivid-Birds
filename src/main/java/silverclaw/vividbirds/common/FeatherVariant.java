package silverclaw.vividbirds.common;

public enum FeatherVariant implements NamedResource {

	LYREBIRD,
	VULTURE,
	OSTRICH,
	SEAGULL;
	
	public int getMetaData() {
		return ordinal() + 1;
	}

	@Override
	public String getResourceName() {
		
		return VividBirds.MODID + ":feather_" + name().toLowerCase();
	}
}
