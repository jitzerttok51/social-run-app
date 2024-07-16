
export interface Violation {
    message: string,
    type: 'object' | 'property',
    property: string
}