import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import { TreeView, TreeItem } from '@mui/lab';
import Paper from '@mui/material/Paper';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import ChevronRightIcon from '@mui/icons-material/ChevronRight';

export default function Content(props: any) {
    let pathItems: string[] = [];
    props.data.forEach((item: any) => {
        pathItems.push(item.path ? item.path : item.name);
    })

    type TreeNode = {
        nodeId: string;
        label: string;
        children: TreeNode[] | null;
    };

    type Props = {
        pathItems: string[];
    };

    const buildTreeView = ({ pathItems }: Props): JSX.Element => {
        const generateTreeItems = (pathArray: string[]): TreeNode[] => {
            const root: TreeNode = { nodeId: '0', label: '', children: [] };
            const treeMap: Record<string, TreeNode> = { '0': root };

            pathArray.forEach((path, index) => {
                const pathSegments = path.split('/').filter((segment) => segment !== '');
                let parentNode: TreeNode = root;

                pathSegments.forEach((segment, segmentIndex) => {
                    const nodeId = `${index + 1}_${segment}`;
                    const label = segment;
                    const isLeafNode = segmentIndex === pathSegments.length - 1;

                    const existingNode = parentNode.children?.find((node) => node.label === label);
                    if (existingNode) {
                        parentNode = existingNode;
                    } else {
                        const newNode: TreeNode = {
                            nodeId,
                            label,
                            children: [],
                        };
                        parentNode.children?.push(newNode);
                        parentNode = newNode;
                    }

                    if (isLeafNode) {
                        parentNode.children = null;
                    }

                    treeMap[nodeId] = parentNode;
                });
            });

            return root.children || [];
        };

        const renderTreeItems = (nodes: TreeNode[]): JSX.Element[] => {
            return nodes.map((node) => {
                return (
                    <TreeItem
                        key={node.nodeId}
                        nodeId={node.nodeId}
                        label={node.label}
                        onClick={(e) => {
                            console.log(e)
                        }}
                    >
                        {node.children ? renderTreeItems(node.children) : null}
                    </TreeItem>
                );
            });
        };

        const treeItems = generateTreeItems(pathItems);

        return (
            <TreeView
                aria-label="file system navigator"
                defaultCollapseIcon={<ExpandMoreIcon />}
                defaultExpandIcon={<ChevronRightIcon />}
                sx={{
                    height: 240,
                    flexGrow: 1,
                    maxWidth: 400,
                    overflowY: 'auto',
                    textAlign: 'left',
            }}
            >
                {renderTreeItems(treeItems)}
            </TreeView>
        );
    };

    return (
        <Paper sx={{maxWidth: 936, margin: 'auto', overflow: 'hidden'}}>
            <AppBar
                position="static"
                color="default"
                elevation={0}
                sx={{borderBottom: '1px solid rgba(0, 0, 0, 0.12)'}}
            >
            </AppBar>
            {buildTreeView({ pathItems })}
        </Paper>
    );
}
