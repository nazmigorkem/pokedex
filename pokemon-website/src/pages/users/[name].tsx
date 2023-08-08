import { GetServerSideProps } from 'next';

export default function Name({ name }: { name: string }) {
	return <div>{name}</div>;
}

export const getServerSideProps: GetServerSideProps<{
	name: string;
}> = async ({ query }) => {
	return { props: { name: query.name as string } };
};
