export default function NotFound() {
	return (
		<div className="flex justify-center items-center min-h-screen gap-3">
			<span className="text-5xl font-bold">404</span>
			<div className="h-[6rem] bg-error w-[0.1px] rounded-md" />
			<span className="text-2xl font-bold">Page was not found.</span>
		</div>
	);
}
