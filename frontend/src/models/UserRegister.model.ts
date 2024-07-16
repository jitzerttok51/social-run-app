
export interface UserRegister {
    username?: string | null,
    email?: string | null,
    password?: string | null,
    confirmPassword?: string | null,
    firstName?: string | null,
    lastName?: string | null,
    sex?: 'MALE' | 'FEMALE' | null,
    dateOfBirth?: Date | null
}

export function mergeToObject([username, email, firstName, lastName, password, confirmPassword, dateOfBirth, sex]: [string, string, string, string, string, string, Date, "MALE" | "FEMALE"]) {
    return {
        username, email, password, confirmPassword, firstName, lastName, sex, dateOfBirth
    } as UserRegister
}