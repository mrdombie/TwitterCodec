/**
 * 
 */
package utils.constants;

public enum FileLocations {
    
	DIRECTORY("D:/Programming/Projects/TwitterCodec/output/template.txt");

    private FileLocations(final String text) {
        this.text = text;
    }

    private final String text;
    @Override
    public String toString() {
        return text;
    }
}