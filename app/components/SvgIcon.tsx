export default function SvgIcon(props: {heading: string, svg: JSX.Element}){

    return (
        <div className={"flex items-center"}>
            {props.svg}
            <span className={"text-xl ml-2"}>{props.heading}</span>
        </div>
)
}



