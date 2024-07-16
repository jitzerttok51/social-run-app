import { Violation } from "./Violation.model"

export interface ViolationResponse {
    message: string
    errors: Violation[]
}