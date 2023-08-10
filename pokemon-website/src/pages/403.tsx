export default function Forbidden() {
	return (
		<div className="flex justify-center items-center min-h-screen gap-3">
			<span className="text-5xl font-bold">403</span>
			<div className="h-[6rem] bg-error w-[0.1px] rounded-md" />
			<span className="text-2xl font-bold">Access denied.</span>
		</div>
	);
}
