import { ChangeEvent, HTMLInputTypeAttribute } from 'react';

export default function CustomInput({
	label,
	onChange,
	type,
	placeholder,
	maxLength,
	value,
}: {
	label: string;
	onChange: (e: ChangeEvent<HTMLInputElement>) => void;
	type: HTMLInputTypeAttribute;
	placeholder: string;
	maxLength?: number;
	value?: string;
}) {
	return (
		<div>
			<label className="label">{label}</label>
			<input
				value={value}
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
