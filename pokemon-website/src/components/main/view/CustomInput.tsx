import { ChangeEvent, HTMLInputTypeAttribute } from 'react';

export default function CustomInput({
	label,
	onChange,
	type,
	placeholder,
	maxLength,
}: {
	label: string;
	onChange: (e: ChangeEvent<HTMLInputElement>) => void;
	type: HTMLInputTypeAttribute;
	placeholder: string;
	maxLength?: number;
}) {
	return (
		<div>
			<label className="label">{label}</label>
			<input
				min={0}
				minLength={maxLength}
				maxLength={maxLength ?? 255}
				onChange={onChange}
				type={type}
				placeholder={placeholder}
				className="input input-accent input-bordered w-full"
			/>
		</div>
	);
}
