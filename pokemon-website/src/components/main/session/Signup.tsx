import ErrorList from '#/components/main/view/ErrorList';
import { SERVER_URL } from '#/endpoints/Fetcher';
import { USER_SERVER_ENDPOINTS } from '#/endpoints/User';
import { useRouter } from 'next/router';
import { useState } from 'react';

export default function Signup() {
	const [values, setValues] = useState({ username: '', password: '', confirmPassword: '', agreed: false, errors: [] as string[], success: false });
	const router = useRouter();

	return (
		<>
			<div className="btn btn-accent btn-outline w-1/2" onClick={() => (window as any).sign_up_modal.showModal()}>
				Sign Up
			</div>
			<dialog id="sign_up_modal" className="modal">
				<form method="dialog" className="modal-box flex flex-col gap-5">
					{values.errors.length !== 0 && <ErrorList errors={values.errors} />}
					<h3 className="font-bold text-lg">Hello! Fill out the form down below to sign up.</h3>
					<input
						type="text"
						placeholder="Username"
						onChange={(e) => {
							setValues({ ...values, username: e.target.value });
						}}
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
					<input
						type="password"
						placeholder="Confirm Password"
						onChange={(e) => {
							setValues({ ...values, confirmPassword: e.target.value });
						}}
						className="input input-bordered"
					/>
					<div className="form-control">
						<label className="cursor-pointer label">
							<span className="label-text">I agree to the terms and conditions.</span>
							<input
								onChange={(e) => {
									setValues({ ...values, agreed: e.target.checked });
								}}
								type="checkbox"
								className="checkbox"
							/>
							<span className="checkbox-mark"></span>
						</label>
					</div>
					<div className="flex gap-5">
						<div
							className="btn btn-accent"
							onClick={async () => {
								if (values.username === '') return setValues({ ...values, errors: ['Username cannot be empty!'] });
								if (values.password === '') return setValues({ ...values, errors: ['Password cannot be empty!'] });
								if (values.password !== values.confirmPassword) return setValues({ ...values, errors: ['Passwords do not match!'] });
								if (!values.agreed) return setValues({ ...values, errors: ['You must agree to the terms and conditions.'] });

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
								if (response.status !== 200) {
									setValues({ ...values, errors: data.errors });
								} else {
									router.reload();
									setValues({ ...values, errors: [], success: true });
								}
							}}
						>
							Sign Up
						</div>
						<button className="btn btn-ghost" onClick={() => (window as any).sign_up_modal.close()}>
							Cancel
						</button>
					</div>
				</form>
				<form method="dialog" className="modal-backdrop">
					<button className="focus:outline-0">close</button>
				</form>
			</dialog>
		</>
	);
}
