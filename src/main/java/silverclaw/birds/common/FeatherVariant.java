package silverclaw.birds.common;

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
		
		return Birds.MODID + ":feather_" + name().toLowerCase();
	}
}
