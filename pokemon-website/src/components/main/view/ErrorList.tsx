export default function ErrorList({ errors }: { errors: string[] }) {
	return (
		<>
			{errors.length !== 0 && (
				<div className="alert alert-error rounded-lg">
					<ul>
						{errors.map((error, index) => (
							<li key={index}>{error}</li>
						))}
					</ul>
				</div>
			)}
		</>
	);
}
