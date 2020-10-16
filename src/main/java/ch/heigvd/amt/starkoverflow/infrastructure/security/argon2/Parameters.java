package ch.heigvd.amt.starkoverflow.infrastructure.security.argon2;

public final class Parameters {
    private Parameters(){}

    public static int ITERATIONS = 22;
    public static int MEMORY = 256 * 256;
    public static int PARALLELISM = 1;
    public static int SALT_LENGTH = 32;
    public static int HASH_LENGTH = 64;

}
