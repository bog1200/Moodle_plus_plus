import { signIn } from "@/auth"

export default async function SignInPage() {
    return (
        <div className="w-full h-screen flex flex-col justify-center items-center  bg-gray-500">
            <div className={"p-24 bg-gray-700 flex justify-center items-center flex-col rounded-xl gap-12"}>
               <div className={"grid grid-cols-2 grid-rows-1 w-full"}>
                       <form
                           className={"w-40 bg-gray-500 text-center border-2 border-foreground rounded-xl p-4 hover:bg-foreground hover:text-background"}
                           action={async () => {
                               "use server"
                               await signIn("bananaidp", {redirectTo: "/dashboard"})
                           }
                           }
                       >
                           <button type="submit">
                               <span>Banana IDP</span>
                           </button>
                       </form>
                       <form
                           className={"w-40 bg-gray-500 text-center border-2 border-foreground rounded-xl p-4 hover:bg-foreground hover:text-background"}
                           action={async () => {
                               "use server"
                               await signIn("orangeidp", {redirectTo: "/dashboard"})
                           }
                           }
                       >
                           <button type="submit">
                               <span>Orange IDP</span>
                           </button>
                       </form>
               </div>
                <form
                    className={"w-80 bg-gray-500 text-center border-2 border-foreground rounded-xl p-4 hover:bg-foreground hover:text-background"}
                    action={async () => {
                        "use server"
                        await signIn("romailsso", {redirectTo: "/dashboard"})
                    }
                    }
                >
                    <button type="submit">
                        <span>Sign in with Romail SSO</span>
                    </button>
                </form>
                <form
                    className={"w-80 bg-gray-500   text-center border-2 border-foreground rounded-xl p-4 hover:bg-foreground hover:text-background"}
                    action={async () => {
                        "use server"
                        await signIn("github", {redirectTo: "/dashboard"})
                    }
                    }
                >
                    <button type="submit">
                        <span>Sign in with GitHub</span>
                    </button>
                </form>
            </div>

        </div>
    )
}