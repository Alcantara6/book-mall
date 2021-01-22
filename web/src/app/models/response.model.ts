export interface Page<T> {
	/** 当前页的内容 */
	content: T[];
	/** 每页数量 */
	size: number;
	/** 总数 */
	totalElements: number;
}
