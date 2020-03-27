/**
 * Created by sujayjayaram on 02/02/2020.
 */

namespace Validation {
    // export needed here only if using class is in same namespace
    export interface StringValidator {
        isAcceptable(s: string): boolean;
    }
}
