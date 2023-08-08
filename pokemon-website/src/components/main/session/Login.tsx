import { SERVER_URL } from '#/endpoints/Fetcher';
import { useRouter } from 'next/router';
import { useState } from 'react';
import ErrorList from '../view/ErrorList';
import { USER_SERVER_ENDPOINTS } from '#/endpoints/User';

export default function Login() {
	const [values, setValues] = useState({ username: '', password: '', errors: [] as string[], success: false });
	const router = useRouter();
	return (
		<>
			<div className="btn btn-accent w-1/2" onClick={() => (window as any).login_modal.showModal()}>
				Login
			</div>
			<dialog id="login_modal" className="modal">
				<form method="dialog" className="modal-box flex flex-col gap-5">
					<ErrorList errors={values.errors} />
					<h3 className="font-bold text-lg">Hello! Fill out the form down below to login.</h3>
					<input
						type="text"
						onChange={(e) => {
							setValues({ ...values, username: e.target.value });
						}}
						placeholder="Username"
						className="input input-bordered"
					/>
					<input
						type="password"
						placeholder="Password"
						onChange={(e) => {
							setValues({ ...values, password: e.target.value });
						}}
						className="input input-bordered"
					/>
					<div className="flex gap-5">
						<div
							onClick={async () => {
								if (values.username === '' || values.password === '') {
									setValues({ ...values, errors: ['Please fill out all fields.'] });
									return;
								}
								const response = await fetch(`${SERVER_URL}${USER_SERVER_ENDPOINTS.LOGIN}`, {
									method: 'POST',
									headers: {
										'Content-Type': 'application/json',
									},
									body: JSON.stringify({
										username: values.username,
										password: values.password,
									}),
								});
								const data = await response.json();
								if (data.errors) {
									setValues({ ...values, errors: data.errors });
									return;
								}

								router.reload();
							}}
							className="btn btn-accent"
						>
							Login
						</div>
						<button className="btn btn-ghost">Cancel</button>
					</div>
				</form>
				<form method="dialog" className="modal-backdrop">
					<button className="focus:outline-0">close</button>
				</form>
			</dialog>
		</>
	);
}
