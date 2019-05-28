package configuration.ordering;

//	Overriding property values
//-When more than one configuration source is used, the properties from all the sources are collected together
// and accessed as a single set by the application.
//-Each configuration source is assigned an ordinal value.
//-If a property appears in more than one source, then the property value from the source with the highest ordinal
// takes precedence and is returned to the application. Default ordinal values are:
// • System Properties - 400
// • Environment Variables - 300
// • /META-INF/microprofile-config.properties - 100
// • Custom ConfigSource Objects - The getOrdinal() result of the ConfigSource

//-Note: The first three ordinal values that are listed can be overridden by using a config_ordinal property
// that is located in the configuration source to which it applies.

//-If two ConfigSources that provide the same property have identical ordinals, then the ConfigSources ID is used
// for comparison according to the string comparison rules.
//-Sources that are normally set earlier in the development lifecycle have lesser ordinals and precedence.
// This is to support the ability to override an existing property value later on in the application lifecycle,
// for example during application assembly or install.

public class AnInfo {}