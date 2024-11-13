export default function SvgIcon(props: {heading: string, svg: JSX.Element}){

    return (
        <div className={"flex flex-col lg:flex-row items-center max-w-32 lg:max-w-full text-center lg:text-left"}>
            {props.svg}
            <span className={"text-xl ml-2 "}>{props.heading}</span>
        </div>
)
}



