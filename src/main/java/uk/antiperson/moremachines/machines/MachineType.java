package uk.antiperson.moremachines.machines;

/**
 * Types of machines that can be used.
 */
public enum MachineType {
    MINER,
    PUMP,
    HARVESTER,
    GRINDER;

    /**
     * Matches string to the applicable MachineType. Returns null instead of throwing exception.
     * @param toMatch the string to match.
     * @return MachineType that matches.
     */
    public static MachineType niceMatch(String toMatch) {
        try {
            return MachineType.valueOf(toMatch.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

}
